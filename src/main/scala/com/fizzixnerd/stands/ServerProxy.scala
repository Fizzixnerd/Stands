package com.fizzixnerd.stands
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

class ServerProxy extends IProxy {
  override
  def preInit(event: FMLPreInitializationEvent): Unit = {}

  override
  def init(event: FMLInitializationEvent): Unit = {}

  override
  def postInit(event: FMLPostInitializationEvent): Unit = {}
}
