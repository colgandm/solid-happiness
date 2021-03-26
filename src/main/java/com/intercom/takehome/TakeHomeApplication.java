package com.intercom.takehome;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intercom.takehome.backend.InvitationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class TakeHomeApplication implements CommandLineRunner {

    private static final String S3 = "s3";
    private static final String FILE = "file";
    @Autowired
    private InvitationManager invitationManager;

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication app = new SpringApplication(TakeHomeApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("APPLICATION FINISHED");
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .create();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Override
    public void run(String... args) {
        if (args.length > 0) {
            if (S3.equalsIgnoreCase(args[0])) {
                invitationManager.createInvitationListFromS3();
            } else if (FILE.equalsIgnoreCase(args[0])) {
                invitationManager.createInvitationListFromFile();
            }
        }
    }
}
