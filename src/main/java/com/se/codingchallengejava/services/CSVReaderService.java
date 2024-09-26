package com.se.codingchallengejava.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.se.codingchallengejava.Entities.PostCode;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderService {

    private List<PostCode> postCodeList = new ArrayList<>();

    public void loadCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                String bundesLand = nextLine[2];
                String land = nextLine[4];
                String ort = nextLine[5];
                int postleitzahl = Integer.parseInt(nextLine[6]);
                String bezirk = nextLine[7];
                postCodeList.add(new PostCode(bundesLand, land, ort, postleitzahl, bezirk));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public String getBundesLand(Integer postleitzahl) {
        String bundesLand = "";
        for (PostCode postCode : postCodeList) {
            if (postCode.getPostleitzahl() == postleitzahl) {
                bundesLand = postCode.getBundesland();
            }
        }
        return bundesLand;
    }
}

