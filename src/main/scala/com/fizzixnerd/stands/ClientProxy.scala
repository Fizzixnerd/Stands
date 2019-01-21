package com.fizzixnerd.stands

import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.relauncher.Side

class ClientProxy extends IProxy {
  override
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Stands.logger.info("calling ClientProxy on preInit.")
    StandsPacketHandler.INSTANCE.registerMessage(classOf[TimeStopMessageHandler], classOf[TimeStopMessage], StandsPacketHandler.id, Side.CLIENT)
    StandsPacketHandler.id += 1
  }

  override
  def init(event: FMLInitializationEvent): Unit = {}

  override
  def postInit(event: FMLPostInitializationEvent): Unit = {}
}
