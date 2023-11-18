package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class PortScanner {

    private static final Map<Integer, String> SERVICES = Map.of(
        135, "EPMAP",
        137, "Служба имен NetBIOS",
        139, "Служба сеансов NetBIOS",
        445, "Microsoft-DS Active Directory",
        843, "Adobe Flash",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        17500, "Dropbox",
        27017, "MongoDB"
    );

    public static boolean isTcpPortClosed(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isUdpPortClosed(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static String getServiceName(int port) {
        return SERVICES.getOrDefault(port, "Unknown Service");
    }

}
