package edu.au.cpsc.airportapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Airport {
   private String ident;
   private String type;
   private String name;
   private Integer elevationFt;
   private String continent;
   private String isoCountry;
   private String isoRegion;
   private String municipality;
   private String gpsCode;
   private String iataCode;
   private String localCode;
   private Coordinates coordinates;
   public Airport() {
   }
   public String getIdent() {
      return ident;
   }
   public void setIdent(String ident) {
      this.ident = ident;
   }
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getElevationFt() {
      return elevationFt;
   }
   public void setElevationFt(Integer elevationFt) {
      this.elevationFt = elevationFt;
   }
   public String getContinent() {
      return continent;
   }
   public void setContinent(String continent) {
      this.continent = continent;
   }
   public String getIsoCountry() {
      return isoCountry;
   }
   public void setIsoCountry(String isoCountry) {
      this.isoCountry = isoCountry;
   }
   public String getIsoRegion() {
      return isoRegion;
   }
   public void setIsoRegion(String isoRegion) {
      this.isoRegion = isoRegion;
   }
   public String getMunicipality() {
      return municipality;
   }
   public void setMunicipality(String municipality) {
      this.municipality = municipality;
   }
   public String getGpsCode() {
      return gpsCode;
   }
   public void setGpsCode(String gpsCode) {
      this.gpsCode = gpsCode;
   }
   public String getIataCode() {
      return iataCode;
   }
   public void setIataCode(String iataCode) {
      this.iataCode = iataCode;
   }
   public String getLocalCode() {
      return localCode;
   }
   public void setLocalCode(String localCode) {
      this.localCode = localCode;
   }
   public Coordinates getCoordinates() {
      return coordinates;
   }
   public void setCoordinates(Coordinates coordinates) {
      this.coordinates = coordinates;
   }
   public static List<Airport> readAll() throws IOException {
      List<Airport> airports = new ArrayList<>();

      try (
             InputStream inputStream = Airport.class.getResourceAsStream("/airport-codes.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      ) {
         String line;
         reader.readLine();
         while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            Airport airport = new Airport();
            airport.setIdent(parts[0]);
            airport.setType(parts[1]);
            airport.setName(parts[2]);
            airport.setElevationFt(parts[3].isEmpty() ? null : Integer.parseInt(parts[3]));
            airport.setContinent(parts[4]);
            airport.setIsoCountry(parts[5]);
            airport.setIsoRegion(parts[6]);
            airport.setMunicipality(parts[7]);
            airport.setGpsCode(parts[8].isEmpty() ? "" : parts[8]);
            airport.setIataCode(parts[9].isEmpty() ? "" : parts[9]);
            airport.setLocalCode(parts[10].isEmpty() ? "" : parts[10]);
            String longitude = parts[11];
            String latitude = parts[12];
            airport.setCoordinates(new Coordinates(Double.parseDouble(longitude), Double.parseDouble(latitude)));
            airports.add(airport);
         }
      }
   
      return airports;
   }

   public static class Coordinates {
      private double longitude;
      private double latitude;
      public Coordinates(double longitude, double latitude) {
         this.longitude = longitude;
         this.latitude = latitude;
      }
      public double getLongitude() {
         return longitude;
      }
      public double getLatitude() {
         return latitude;
      }

   }
}
