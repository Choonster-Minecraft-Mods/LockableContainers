package com.github.choonster.lockablecontainers.item;

import com.github.choonster.lockablecontainers.LockableContainers;
import com.github.choonster.lockablecontainers.api.capability.lock.ILock;
import com.github.choonster.lockablecontainers.api.capability.lock.util.LockUtils;
import com.github.choonster.lockablecontainers.network.MessageOpenLockGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * A key that can lock {@link ILock}s.
 *
 * @author Choonster
 */
public class ItemKey extends Item {
	public ItemKey() {
		setRegistryName(LockableContainers.MODID, "key");
		setUnlocalizedName(getRegistryName().toString());
		setMaxStackSize(1);
		setCreativeTab(LockableContainers.TAB);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		final ILock lock = LockUtils.getLock(worldIn, pos, facing);
		if (lock != null) {
			if (!worldIn.isRemote) {
				if (lock.isLocked()) {
					playerIn.addChatComponentMessage(new TextComponentTranslation("message.lockablecontainers:lock.already_locked"));
				} else {
					LockableContainers.NETWORK.sendTo(new MessageOpenLockGui(pos, facing), (EntityPlayerMP) playerIn);
				}
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}
}
