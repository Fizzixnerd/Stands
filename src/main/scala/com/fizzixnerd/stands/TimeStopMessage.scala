package com.fizzixnerd.stands

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class TimeStopMessage(var dimension: Int, var position: BlockPos, var range: Int) extends IMessage {
  override
  def toBytes(buf: ByteBuf): Unit = {
    buf.writeInt(dimension)
    buf.writeInt(position.getX)
    buf.writeInt(position.getY)
    buf.writeInt(position.getZ)
    buf.writeInt(range)
  }

  override
  def fromBytes(buf: ByteBuf): Unit = {
    this.dimension = buf.readInt()
    this.position = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt())
    this.range = buf.readInt()
  }
}
