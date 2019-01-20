package com.fizzixnerd.stands

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}

class TimeStopMessageHandler extends IMessageHandler[TimeStopMessage, IMessage] {
  override
  def onMessage(message: TimeStopMessage, ctx: MessageContext): IMessage = {
    val clientPlayer = Minecraft.getMinecraft.player
    message
  }
}
