package com.anil.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class SystemService {

    public String getHostname() {
        return run("hostname");
    }

    public String getUptime() {
        try {
            String output = run("uptime");

            // Find the line starting with "Statistics since"
            for (String line : output.split("\n")) {
                if (line.toLowerCase().contains("statistics since")) {
                    return line.trim();
                }
            }

            return "Could not detect uptime.";

        } catch (Exception e) {
            return "Error getting uptime: " + e.getMessage();
        }
    }

    public String getCpu() {
        return run("wmic cpu get loadpercentage"); // Windows
    }

    public String getMemory() {
        return run("wmic OS get FreePhysicalMemory,TotalVisibleMemorySize /Value");
    }

    public String getDisk() {
        return run("wmic logicaldisk get caption,freespace,size");
    }

    public String run(String command) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder builder;

            // ---------- OS-dependent commands ----------
            if (os.contains("win")) {

                // Windows replacements
                switch (command) {
                    case "uptime":
                        builder = new ProcessBuilder("cmd.exe", "/c", "net stats workstation");
                        break;

                    case "hostname":
                        builder = new ProcessBuilder("cmd.exe", "/c", "hostname");
                        break;

                    default:
                        builder = new ProcessBuilder("cmd.exe", "/c", command);
                }

            } else {
                // Linux / Mac (uptime exists)
                switch (command) {
                    case "uptime":
                        builder = new ProcessBuilder("sh", "-c", "uptime -p"); // “up 3 hours…”
                        break;

                    case "hostname":
                        builder = new ProcessBuilder("sh", "-c", "hostname");
                        break;

                    default:
                        builder = new ProcessBuilder("sh", "-c", command);
                }
            }

            // ---------- Execute ----------
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            
            return result.toString();

        } catch (Exception e) {
            return "Error running command: " + e.getMessage();
        }
    }

}
