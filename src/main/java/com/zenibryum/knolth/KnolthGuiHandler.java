package com.zenibryum.knolth;

import com.zenibryum.knolth.gui.ContainerKnolthTileEntity;
import com.zenibryum.knolth.gui.GuiManual;
import com.zenibryum.knolth.tileentity.KnolthTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class KnolthGuiHandler implements IGuiHandler {
	public static final int KNOLTH_TILE_ENTITY_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if (ID == KNOLTH_TILE_ENTITY_GUI)
	        return new ContainerKnolthTileEntity(player.inventory, (KnolthTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
	    return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == KNOLTH_TILE_ENTITY_GUI)
		{
			return new GuiManual();
	    }
	    return null;
	}
}
