package com.fizzixnerd.stands

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock

class BlockCool extends Block(Material.GROUND) {
  setRegistryName("cool")
  setUnlocalizedName("cool")
  setCreativeTab(CreativeTabs.DECORATIONS)

  val itemBlock: ItemBlock = new ItemBlock(this)
  itemBlock.setRegistryName(getRegistryName)
  itemBlock.setUnlocalizedName("cool")
}
