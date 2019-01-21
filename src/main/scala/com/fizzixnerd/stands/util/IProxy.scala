package com.fizzixnerd.stands.util

import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

trait IProxy {
  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit

  @EventHandler
  def init(event: FMLInitializationEvent): Unit

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit
}
