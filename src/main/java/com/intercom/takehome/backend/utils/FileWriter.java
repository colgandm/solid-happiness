package com.intercom.takehome.backend.utils;

import com.intercom.takehome.backend.model.Invitation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@Slf4j
public class FileWriter {

    @Value("${output.file.name}")
    private String outputFileName;

    public void writeInvitationsToFile(List<Invitation> invitations) {
        log.info("Writing {} invitations to file : {} ", invitations.size(), outputFileName);
        try {
            File fileOut = new File(outputFileName);
            FileOutputStream fos = new FileOutputStream(fileOut);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (Invitation invitation : invitations) {
                bw.write(invitation.toString());
                bw.newLine();
            }
            bw.close();
            fos.close();
        } catch (IOException ex) {
            log.error("Error Occurred writing output file.", ex);
        }
    }
}

