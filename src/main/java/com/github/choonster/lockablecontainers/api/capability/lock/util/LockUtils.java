package com.github.choonster.lockablecontainers.api.capability.lock.util;

import com.github.choonster.lockablecontainers.api.capability.lock.CapabilityLock;
import com.github.choonster.lockablecontainers.api.capability.lock.ILock;
import com.github.choonster.lockablecontainers.api.capability.lock.wrapper.LockableContainerWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

import javax.annotation.Nullable;

/**
 * Utility methods for dealing with {@link ILock}s.
 *
 * @author Choonster
 */
public class LockUtils {

	/**
	 * Get the {@link ILock} from a block.
	 *
	 * @param world The world
	 * @param pos   The position
	 * @return The ILock, or null if there isn't one.
	 */
	@Nullable
	public static ILock getLock(IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
		final IBlockState state = world.getBlockState(pos);

		if (state.getBlock().hasTileEntity(state)) {
			final TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity != null) {
				if (tileEntity.hasCapability(CapabilityLock.LOCK_CAPABILITY, side)) {
					return tileEntity.getCapability(CapabilityLock.LOCK_CAPABILITY, side);
				} else if (tileEntity instanceof ILockableContainer) {
					return new LockableContainerWrapper((ILockableContainer) tileEntity);
				}
			}
		}

		return null;
	}

	/**
	 * Try to open the {@link ILock}, notifying the player if they can't.
	 *
	 * @param lock The lock
	 * @param player The player opening the lock
	 * @return Was the player allowed to open the lock?
	 */
	public static boolean tryOpen(ILock lock, EntityPlayer player) {
		// Adapted from EntityPlayerMP#displayGUIChest

		if (!lock.canOpen(player)) {
			if (player instanceof EntityPlayerMP) {
				final EntityPlayerMP playerMP = (EntityPlayerMP) player;
				playerMP.connection.sendPacket(new SPacketChat(new TextComponentTranslation("container.isLocked", lock.getDisplayNameSource().getDisplayName()), (byte) 2));
				playerMP.connection.sendPacket(new SPacketSoundEffect(SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, playerMP.posX, playerMP.posY, playerMP.posZ, 1.0F, 1.0F));
			}

			return false;
		}

		return true;
	}
}
