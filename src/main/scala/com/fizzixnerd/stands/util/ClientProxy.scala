package com.fizzixnerd.stands.util

import com.fizzixnerd.stands.abilities.timestop.{TimeStopMessage, TimeStopMessageHandler}
import com.fizzixnerd.stands.Stands
import com.fizzixnerd.stands.net.StandsPacketHandler
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
