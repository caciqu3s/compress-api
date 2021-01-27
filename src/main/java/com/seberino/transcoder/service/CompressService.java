package com.seberino.transcoder.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CompressService {

    public static void exec(File input) throws IOException {
        Process start = Runtime.getRuntime().exec(new String[]
                { "ffmpeg", "-i", input.getPath(), "-s", "640x480", "-crf", "18", "output.mp4" });
        BufferedReader r = new BufferedReader(
                new InputStreamReader(start.getErrorStream()));
        String line = null;
        while ((line = r.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
