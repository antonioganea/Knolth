package com.zenibryum.knolth.blocks;

import java.util.Random;

import com.zenibryum.knolth.Knolth;
import com.zenibryum.knolth.init.KnolthBlocks;
import com.zenibryum.knolth.tileentity.TileEntityGrinder;
import com.zenibryum.knolth.tileentity.TileEntityMulti;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMulti extends BlockContainer
{
    //public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static boolean hasTileEntity;

    public BlockMulti()
    {
        super(Material.rock);
        //setUnlocalizedName("grinder");
        //setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        //setCreativeTab();
        stepSound = soundTypeSnow;
        blockParticleGravity = 1.0F;
        slipperiness = 0.6F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        lightOpacity = 20; // cast a light shadow
        setTickRandomly(false);
        useNeighborBrightness = false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KnolthBlocks.multi_block_part);
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState thisState)
    {
        //IBlockState thisState = worldIn.getBlockState(pos);
    	for ( EnumFacing neighbour : EnumFacing.HORIZONTALS ){
	        
	        Vec3i dirVecN = neighbour.getDirectionVec();
    		IBlockState state = worldIn.getBlockState(pos.add(dirVecN));
    		if ( state.getBlock() == KnolthBlocks.multi_block_edge){
	        EnumFacing direction = state.getValue(BlockMultiEdge.FACING);
	        Vec3i dirVec = direction.getDirectionVec();
	        
	        //System.out.println("CHECK");
	        
	        if ( neighbour.getOpposite() == direction )
	        {
	        	worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX(), pos.getY() + dirVec.getY(), pos.getZ() + dirVec.getZ()), true );
	        	worldIn.destroyBlock( new BlockPos(pos.getX() - dirVec.getX(), pos.getY() - dirVec.getY(), pos.getZ() - dirVec.getZ()), true );
	        }
    		}
	       
    	}
    	this.dropBlockAsItem(worldIn, pos, thisState, 0);
    }
    
    
    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        //IBlockState thisState = worldIn.getBlockState(pos);
    	for ( EnumFacing neighbour : EnumFacing.HORIZONTALS ){
	        
	        Vec3i dirVecN = neighbour.getDirectionVec();
    		IBlockState state = worldIn.getBlockState(pos.add(dirVecN));
    		if ( state.getBlock() == KnolthBlocks.multi_block_edge){
	        EnumFacing direction = state.getValue(BlockMultiEdge.FACING);
	        Vec3i dirVec = direction.getDirectionVec();
	        
	        //System.out.println("CHECK");
	        
	        if ( neighbour.getOpposite() == direction )
	        {
	        	worldIn.destroyBlock( new BlockPos(pos.getX() + dirVec.getX(), pos.getY() + dirVec.getY(), pos.getZ() + dirVec.getZ()), true );
	        	worldIn.destroyBlock( new BlockPos(pos.getX() - dirVec.getX(), pos.getY() - dirVec.getY(), pos.getZ() - dirVec.getZ()), true );
	        }
    		}
	       
    	}
    	this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
    }

    @Override
    public void onBlockAdded(World parWorld, BlockPos parBlockPos, IBlockState parIBlockState)
    {
        if (!parWorld.isRemote)
        {
        	
        	
        	
        	
        	
        	
        	
        	
            // Rotate block if the front side is blocked
            /*Block blockToNorth = parWorld.getBlockState(
                  parBlockPos.north()).getBlock();
            Block blockToSouth = parWorld.getBlockState(
                  parBlockPos.south()).getBlock();
            Block blockToWest = parWorld.getBlockState(
                  parBlockPos.west()).getBlock();
            Block blockToEast = parWorld.getBlockState(
                  parBlockPos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)parIBlockState.getValue(FACING);
            

            if (enumfacing == EnumFacing.NORTH 
                  && blockToNorth.isFullBlock() 
                  && !blockToSouth.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH 
                  && blockToSouth.isFullBlock() 
                  && !blockToNorth.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST 
                  && blockToWest.isFullBlock() 
                  && !blockToEast.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST 
                  && blockToEast.isFullBlock() 
                  && !blockToWest.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            parWorld.setBlockState(parBlockPos, parIBlockState
                  .withProperty(FACING, enumfacing), 2);
            */
        }
    }

    @Override
    public boolean onBlockActivated(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState, 
          EntityPlayer parPlayer, 
          EnumFacing parSide, 
          float hitX, 
          float hitY, 
          float hitZ)
    {
        if (!parWorld.isRemote)
        {
            parPlayer.openGui(Knolth.instance, Knolth.GUI_ENUM.MULTI.ordinal(), parWorld, parBlockPos.getX(), parBlockPos.getY(), parBlockPos.getZ()); 
        }
        
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
    	// DEBUG
    	System.out.println("BlockMulti createNewTileEntity()");
        return new TileEntityMulti();
    }
/*
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return getDefaultState().withProperty(FACING, 
        		placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
*/

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
    	System.out.println("breakBlock triggered");
        if (!hasTileEntity)
        {
        	System.out.println("working..");
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityMulti)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityMulti)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(KnolthBlocks.multi_block_part);
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }
/*
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }
*/
/*
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }


    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
*/
    
/*
    @SideOnly(Side.CLIENT)
    static final class SwitchEnumFacing
    {
        static final int[] enumFacingArray = new int[EnumFacing.values().length];

        static
        {
            try
            {
                enumFacingArray[EnumFacing.WEST.ordinal()] = 1;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.EAST.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.NORTH.ordinal()] = 3;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.SOUTH.ordinal()] = 4;
            }
            catch (NoSuchFieldError var1)
            {
                // You should improve the error handling here
            }
        }
    }
*/
}    

