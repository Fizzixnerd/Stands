package com.fizzixnerd.stands.items

import net.minecraft.block.BlockDirt
import net.minecraft.block.BlockDirt.DirtType
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Blocks, SoundEvents}
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util._
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.IPlantable
import net.minecraftforge.event.ForgeEventFactory
import net.minecraftforge.fml.common.registry.GameRegistry

class ItemWheatAndSteel extends Item {

  setRegistryName("wheat_and_steel")
  setUnlocalizedName("wheat_and_steel")
  maxStackSize = 1
  setMaxDamage(64)
  setCreativeTab(CreativeTabs.TOOLS)
  private val virtualSeeds = GameRegistry.findRegistry(classOf[Item]).getValue(new ResourceLocation("minecraft:wheat_seeds")).asInstanceOf[Item with IPlantable]

  def canHoe(player: EntityPlayer,
             world: World,
             pos: BlockPos,
             hand: EnumHand,
             side: EnumFacing): Boolean = {
    val itemStack = player.getHeldItem(hand)
    if (!player.canPlayerEdit(pos, side, itemStack)) {
      false
    } else {
      val blockState = world.getBlockState(pos)
      val block = blockState.getBlock
      side != EnumFacing.DOWN && world.isAirBlock(pos.up) && (block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.DIRT)
    }
  }

  def canPlant(player: EntityPlayer,
               world: World,
               pos: BlockPos,
               hand: EnumHand,
               side: EnumFacing): Boolean = {
    val itemStack = player.getHeldItem(hand)
    val blockState = world.getBlockState(pos)
    val block = blockState.getBlock
    val canGrow = block.canSustainPlant(blockState, world, pos, EnumFacing.UP, virtualSeeds)
    player.canPlayerEdit(pos.offset(side), side, itemStack) && canGrow && world.isAirBlock(pos.up)
  }

  def hoe(player: EntityPlayer,
          world: World,
          pos: BlockPos,
          hand: EnumHand,
          side: EnumFacing): EnumActionResult = {
    if (!canHoe(player, world, pos, hand, side)) {
      EnumActionResult.FAIL
    } else {
      val itemStack = player.getHeldItem(hand)
      val hook = ForgeEventFactory.onHoeUse(itemStack, player, world, pos)
      if (hook != 0) {
        return if (hook > 0) EnumActionResult.SUCCESS else EnumActionResult.FAIL
      }
      val blockState = world.getBlockState(pos)
      val block = blockState.getBlock

      if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
        setBlock(itemStack, player, world, pos, Blocks.FARMLAND.getDefaultState)
        return EnumActionResult.SUCCESS
      }
      if (block == Blocks.DIRT) {
        blockState.getValue(BlockDirt.VARIANT) match {
          case DirtType.DIRT =>
            setBlock(itemStack, player, world, pos, Blocks.FARMLAND.getDefaultState)
            return EnumActionResult.SUCCESS
          case DirtType.COARSE_DIRT =>
            setBlock(itemStack, player, world, pos, Blocks.DIRT.getDefaultState.withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT))
            return EnumActionResult.SUCCESS
          case DirtType.PODZOL =>
            return EnumActionResult.PASS
        }
      }
      EnumActionResult.PASS
    }
  }

  def plant(player: EntityPlayer,
            world: World,
            pos: BlockPos,
            hand: EnumHand,
            side: EnumFacing): EnumActionResult = {
    if (!canPlant(player, world, pos, hand, side)) {
      EnumActionResult.FAIL
    } else {
      world.setBlockState(pos.up, virtualSeeds.getPlant(world, pos))
      EnumActionResult.SUCCESS
    }
  }

  def setBlock(itemStack: ItemStack,
               player: EntityPlayer,
               world: World,
               pos: BlockPos,
               state: IBlockState): Unit = {
    world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F)
    if (!world.isRemote) {
      world.setBlockState(pos, state, 11)
    }
  }

  override
  def onItemUse(player: EntityPlayer,
                world: World,
                pos: BlockPos,
                hand: EnumHand,
                side: EnumFacing,
                hitX: Float,
                hitY: Float,
                hitZ: Float): EnumActionResult = {
    val itemStack = player.getHeldItem(hand)
    val success = hoe(player, world, pos, hand, side)
    if (success != EnumActionResult.SUCCESS) {
      success
    } else {
      plant(player, world, pos, hand, side)
      itemStack.damageItem(1, player)
      EnumActionResult.SUCCESS
    }
  }
}
