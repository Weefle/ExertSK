package net.andrew.utils.packets;

public class CheckReceivePacket {
	private static Boolean newPacket = false;
	public static Boolean checkNewPacket(){
		return CheckReceivePacket.newPacket;
	}
}
