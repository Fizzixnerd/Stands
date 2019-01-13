package com.fizzixnerd.stands

import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.logging.log4j.Logger

@Mod(modid = Stands.MODID, version = Stands.VERSION, modLanguage = "scala")
object Stands {
  final val MODID = "stands"
  final val VERSION = "1.0"

  var logger: Logger = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    logger = event.getModLog
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    logger.info(s"Dirt Block is ${Blocks.DIRT.getRegistryName}")
  }
}
