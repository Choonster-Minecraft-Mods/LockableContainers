package com.github.choonster.lockablecontainers.init;


import com.github.choonster.lockablecontainers.api.capability.lock.ILock;
import com.github.choonster.lockablecontainers.api.capability.lock.Lock;
import com.github.choonster.lockablecontainers.util.ConstantDisplayNameSource;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.LockCode;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Registers this mod's capabilities.
 *
 * @author Choonster
 */
public class ModCapabilities {

	/**
	 * Register this mod's capabilities.
	 */
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(ILock.class, new Capability.IStorage<ILock>() {
			@Override
			public NBTBase writeNBT(Capability<ILock> capability, ILock instance, EnumFacing side) {
				final NBTTagCompound tagCompound = new NBTTagCompound();

				final LockCode lockCode = instance.getLockCode();
				lockCode.toNBT(tagCompound);

				return tagCompound;
			}

			@Override
			public void readNBT(Capability<ILock> capability, ILock instance, EnumFacing side, NBTBase nbt) {
				if (!(instance instanceof Lock))
					throw new RuntimeException("Can not deserialise to an instance that isn't the default implementation");

				final Lock lock = (Lock) instance;
				final NBTTagCompound tagCompound = (NBTTagCompound) nbt;

				lock.setLockCode(LockCode.fromNBT(tagCompound));
			}
		}, () -> new Lock(new ConstantDisplayNameSource(new TextComponentTranslation("container.inventory"))));
	}

}
