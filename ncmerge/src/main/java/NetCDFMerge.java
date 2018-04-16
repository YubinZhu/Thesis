import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ucar.ma2.*;
import ucar.nc2.*;
import ucar.nc2.write.Nc4ChunkingDefault;

/**
 * Created by 朱宇斌 on 2018/4/10
 */

public class NetCDFMerge {

    public static void thflxMerge() throws IOException, InvalidRangeException{
        String target = "misc/thflx.nc";

        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        Dimension timeDim;
        Dimension latDim;
        Dimension lonDim;

        Variable lon;
        Variable lat;
        Variable time;
        Variable date;
        Variable timePlot;
        Variable thflx;

        boolean mark = true;
        for (int year = 2000; year <= 2018; year += 1) {
            String source = "misc/thflx/thflx." + year + ".nc";
            NetcdfFile file = NetcdfFile.open(source);

            if (mark) {
                mark = false;

                List<Attribute> attributes = file.getGlobalAttributes();
                for (Attribute attribute: attributes) {
                    writer.addGroupAttribute(null, attribute);
                }

                timeDim = writer.addUnlimitedDimension("time");
                latDim = writer.addDimension(null, "lat", 418);
                lonDim = writer.addDimension(null, "lon", 360);

                lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
                lon.addAll(file.findVariable("lon").getAttributes());
                lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
                lat.addAll(file.findVariable("lat").getAttributes());
                time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
                time.addAll(file.findVariable("time").getAttributes());
                date = writer.addVariable(null, "date", DataType.INT, "time");
                date.addAll(file.findVariable("date").getAttributes());
                timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
                timePlot.addAll(file.findVariable("timePlot").getAttributes());
                thflx = writer.addVariable(null, "thflx", DataType.SHORT, "time lat lon");
                thflx.addAll(file.findVariable("thflx").getAttributes());




            }




            System.out.println(file.getVariables());



            file.close();
            break;
        }

        writer.create();
        writer.close();

        NetcdfFile checker = NetcdfFile.open(target);
        System.out.println(checker.getVariables());
    }
}

        /*Global attributes are referenced by year 2000
        writer.addGroupAttribute(null, new Attribute("creation_date", "Sat Dec 16 20:00:00 MDT 2006"));
        writer.addGroupAttribute(null, new Attribute("sfcHeatFlux", "\nNote that the net surface heat flux are the total surface heat flux \nfrom the NCEP reanalysis 2 plus the relaxation terms."));
        writer.addGroupAttribute(null, new Attribute("time_comment", "The internal time stamp indicates the FIRST day of the averaging period."));
        writer.addGroupAttribute(null, new Attribute("Conventions", "COARDS"));
        writer.addGroupAttribute(null, new Attribute("grib_file", "godas.M.200001-12.grb"));
        writer.addGroupAttribute(null, new Attribute("html_REFERENCES", "http://www.cpc.ncep.noaa.gov/products/GODAS/background.shtml"));
        writer.addGroupAttribute(null, new Attribute("html_BACKGROUND", "http://www.cpc.ncep.noaa.gov/products/GODAS/background.shtml"));
        writer.addGroupAttribute(null, new Attribute("html_GODAS", "www.cpc.ncep.noaa.gov/products/GODAS"));
        writer.addGroupAttribute(null, new Attribute("comment", "NOTE:  THESE ARE THE BIAS CORRECTED GODAS FILES."));
        writer.addGroupAttribute(null, new Attribute("title", "GODAS: Global Ocean Data Assimilation System"));
        writer.addGroupAttribute(null, new Attribute("history", "Created 2006/12 by Hoop"));
        writer.addGroupAttribute(null, new Attribute("References", "http://www.esrl.noaa.gov/psd/data/gridded/data.godas.html"));
        writer.addGroupAttribute(null, new Attribute("dataset_title", "NCEP Global Ocean Data Assimilation System (GODAS)"));

        ArrayList<Float> lon = new ArrayList<Float>();
        ArrayList<Float> lat = new ArrayList<Float>();
        ArrayList<Float> time = new ArrayList<Float>();
        ArrayList<Integer> date = new ArrayList<Integer>();
        ArrayList<Float> timePlot = new ArrayList<Float>();
        ArrayList<Integer[][]> thflx = new ArrayList<Integer[][]>();

        boolean mark = false;
        for (int year = 2000; year <= 2018; year += 1) {
            String path = "misc/thflx/thflx." + year + ".nc";
            System.out.println("Read: " + path);
            NetcdfFile netcdfFile = NetcdfFile.open(path);

            Variable variable;
            Array array;

            if(!mark){
                mark = true;

                variable = netcdfFile.findVariable("lon");
                array = variable.read();
                while(array.hasNext()) {
                    lon.add(array.nextFloat());
                }

                variable = netcdfFile.findVariable("lat");
                array = variable.read();
                while(array.hasNext()) {
                    lat.add(array.nextFloat());
                }

                variable = netcdfFile.findVariable("thflx");
                array = variable.read();
                System.out.println(array.getSize());

            }

            variable = netcdfFile.findVariable("time");
            array = variable.read();
            while(array.hasNext()) {
                time.add(array.nextFloat());
            }

            variable = netcdfFile.findVariable("date");
            array = variable.read();
            while(array.hasNext()) {
                date.add(array.nextInt());
            }

            variable = netcdfFile.findVariable("timePlot");
            array = variable.read();
            while(array.hasNext()) {
                timePlot.add(array.nextFloat());
            }

        }

        System.out.println(lon.size() + lon.toString());
        System.out.println(lat.size() + lat.toString());
        System.out.println(time.size() + time.toString());
        System.out.println(date.size() + date.toString());
        System.out.println(timePlot.size() + timePlot.toString());*/
