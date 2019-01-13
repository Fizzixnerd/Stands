package com.fizzixnerd.stands

import net.minecraft.item.{Item, ItemBlock}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs

object BlockCool extends Block(Material.GROUND) {
  setRegistryName("cool")
  setCreativeTab(CreativeTabs.DECORATIONS)

  val itemBlock: ItemBlock = new ItemBlock(this)
  itemBlock.setRegistryName(getRegistryName)
}
