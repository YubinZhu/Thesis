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

    private static final int START_YEAR = 2000;

    private static final int END_YEAR = 2018;

    public static void thflxMerge() throws IOException, InvalidRangeException {
        String target = "misc/thflx.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable thflx = writer.addVariable(null, "thflx", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = "misc/thflx/thflx." + year + ".nc";
            NetcdfFile file = NetcdfFile.open(source);

            if (mark) {
                mark = false; //do only once

                /* copy global attributes */
                List<Attribute> attributes = file.getGlobalAttributes();
                for (Attribute attribute: attributes) {
                    writer.addGroupAttribute(null, attribute);
                }

                /* copy variable attributes */
                lon.addAll(file.findVariable("lon").getAttributes());
                lat.addAll(file.findVariable("lat").getAttributes());
                time.addAll(file.findVariable("time").getAttributes());
                date.addAll(file.findVariable("date").getAttributes());
                timePlot.addAll(file.findVariable("timePlot").getAttributes());
                thflx.addAll(file.findVariable("thflx").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());

            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(thflx, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("thflx").read());

            file.close();
        }

        writer.close();
    }

    public static void sltflMerge() throws IOException, InvalidRangeException {
        String target = "misc/sltfl.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable sltfl = writer.addVariable(null, "sltfl", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = "misc/sltfl/sltfl." + year + ".nc";
            NetcdfFile file = NetcdfFile.open(source);

            if (mark) {
                mark = false; //do only once

                /* copy global attributes */
                List<Attribute> attributes = file.getGlobalAttributes();
                for (Attribute attribute: attributes) {
                    writer.addGroupAttribute(null, attribute);
                }

                /* copy variable attributes */
                lon.addAll(file.findVariable("lon").getAttributes());
                lat.addAll(file.findVariable("lat").getAttributes());
                time.addAll(file.findVariable("time").getAttributes());
                date.addAll(file.findVariable("date").getAttributes());
                timePlot.addAll(file.findVariable("timePlot").getAttributes());
                sltfl.addAll(file.findVariable("sltfl").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());

            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(sltfl, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("sltfl").read());

            file.close();
        }

        writer.close();
    }

    public static void sshgMerge() throws  IOException, InvalidRangeException {
        String target = "misc/sshg.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable sshg = writer.addVariable(null, "sshg", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = "misc/sshg/sshg." + year + ".nc";
            NetcdfFile file = NetcdfFile.open(source);

            if (mark) {
                mark = false; //do only once

                /* copy global attributes */
                List<Attribute> attributes = file.getGlobalAttributes();
                for (Attribute attribute: attributes) {
                    writer.addGroupAttribute(null, attribute);
                }

                /* copy variable attributes */
                lon.addAll(file.findVariable("lon").getAttributes());
                lat.addAll(file.findVariable("lat").getAttributes());
                time.addAll(file.findVariable("time").getAttributes());
                date.addAll(file.findVariable("date").getAttributes());
                timePlot.addAll(file.findVariable("timePlot").getAttributes());
                sshg.addAll(file.findVariable("sshg").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());

            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(sshg, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("sshg").read());

            file.close();
        }

        writer.close();
    }

}
