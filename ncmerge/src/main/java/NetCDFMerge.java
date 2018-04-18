import java.io.IOException;
import java.util.List;

import ucar.ma2.*;
import ucar.nc2.*;

/**
 * Created by 朱宇斌 on 2018/4/10
 */

public class NetCDFMerge {

    private static final int START_YEAR = 2000;

    private static final int END_YEAR = 2018;

    private static final String SOURCE = "misc/GODAS/";

    public static void thflxMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/thflx.nc";
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
            String source = SOURCE + "thflx/thflx." + year + ".nc";
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
        String target = SOURCE + "merged/sltfl.nc";
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
            String source = SOURCE + "sltfl/sltfl." + year + ".nc";
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
        String target = SOURCE + "merged/sshg.nc";
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
            String source = SOURCE + "sshg/sshg." + year + ".nc";
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

    public static void dbss_obilMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/dbss_obil.nc";
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
        Variable dbss_obil = writer.addVariable(null, "dbss_obil", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "dbss_obil/dbss_obil." + year + ".nc";
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
                dbss_obil.addAll(file.findVariable("dbss_obil").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(dbss_obil, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("dbss_obil").read());

            file.close();
        }

        writer.close();
    }

    public static void dbss_obmlMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/dbss_obml.nc";
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
        Variable dbss_obml = writer.addVariable(null, "dbss_obml", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "dbss_obml/dbss_obml." + year + ".nc";
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
                dbss_obml.addAll(file.findVariable("dbss_obml").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(dbss_obml, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("dbss_obml").read());

            file.close();
        }

        writer.close();
    }

    public static void uflxMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/uflx.nc";
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
        Variable uflx = writer.addVariable(null, "uflx", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "uflx/uflx." + year + ".nc";
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
                uflx.addAll(file.findVariable("uflx").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(uflx, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("uflx").read());

            file.close();
        }

        writer.close();
    }

    public static void vflxMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/vflx.nc";
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
        Variable vflx = writer.addVariable(null, "vflx", DataType.SHORT, "time lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "vflx/vflx." + year + ".nc";
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
                vflx.addAll(file.findVariable("vflx").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(vflx, new int[] {(year - START_YEAR) * 12, 0, 0}, file.findVariable("vflx").read());

            file.close();
        }

        writer.close();
    }

    public static void saltMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/salt.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "level", 40);
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable salt = writer.addVariable(null, "salt", DataType.SHORT, "time level lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "salt/salt." + year + ".nc";
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
                salt.addAll(file.findVariable("salt").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(salt, new int[] {(year - START_YEAR) * 12, 0, 0, 0}, file.findVariable("salt").read());

            file.close();
        }

        writer.close();
    }

    public static void ucurMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/ucur.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "level", 40);
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable ucur = writer.addVariable(null, "ucur", DataType.SHORT, "time level lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "ucur/ucur." + year + ".nc";
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
                ucur.addAll(file.findVariable("ucur").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(ucur, new int[] {(year - START_YEAR) * 12, 0, 0, 0}, file.findVariable("ucur").read());

            file.close();
        }

        writer.close();
    }

    public static void vcurMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/vcur.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "level", 40);
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable vcur = writer.addVariable(null, "vcur", DataType.SHORT, "time level lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "vcur/vcur." + year + ".nc";
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
                vcur.addAll(file.findVariable("vcur").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(vcur, new int[] {(year - START_YEAR) * 12, 0, 0, 0}, file.findVariable("vcur").read());

            file.close();
        }

        writer.close();
    }

    public static void dzdtMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/dzdt.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "level", 40);
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable dzdt = writer.addVariable(null, "dzdt", DataType.SHORT, "time level lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "dzdt/dzdt." + year + ".nc";
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
                dzdt.addAll(file.findVariable("dzdt").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(dzdt, new int[] {(year - START_YEAR) * 12, 0, 0, 0}, file.findVariable("dzdt").read());

            file.close();
        }

        writer.close();
    }

    public static void pottmpMerge() throws IOException, InvalidRangeException {
        String target = SOURCE + "merged/pottmp.nc";
        NetcdfFileWriter writer = NetcdfFileWriter.createNew(NetcdfFileWriter.Version.netcdf3, target, null);

        /* set dimensions */
        writer.addUnlimitedDimension("time");
        writer.addDimension(null, "level", 40);
        writer.addDimension(null, "lat", 418);
        writer.addDimension(null, "lon", 360);

        /* set variables */
        Variable lon = writer.addVariable(null, "lon", DataType.FLOAT, "lon");
        Variable lat = writer.addVariable(null, "lat", DataType.FLOAT, "lat");
        Variable time = writer.addVariable(null, "time", DataType.DOUBLE, "time");
        Variable date = writer.addVariable(null, "date", DataType.INT, "time");
        Variable timePlot = writer.addVariable(null, "timePlot", DataType.FLOAT, "time");
        Variable pottmp = writer.addVariable(null, "pottmp", DataType.SHORT, "time level lat lon");

        boolean mark = true;
        for (int year = START_YEAR; year <= END_YEAR; year += 1) {
            String source = SOURCE + "pottmp/pottmp." + year + ".nc";
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
                pottmp.addAll(file.findVariable("pottmp").getAttributes());

                writer.create();

                /* write data */
                writer.write(lon, new int [1], file.findVariable("lon").read());
                writer.write(lat, new int [1], file.findVariable("lat").read());
            }

            /* write data */
            writer.write(time, new int[] {(year - START_YEAR) * 12}, file.findVariable("time").read());
            writer.write(date, new int[] {(year - START_YEAR) * 12}, file.findVariable("date").read());
            writer.write(timePlot, new int[] {(year - START_YEAR) * 12}, file.findVariable("timePlot").read());
            writer.write(pottmp, new int[] {(year - START_YEAR) * 12, 0, 0, 0}, file.findVariable("pottmp").read());

            file.close();
        }

        writer.close();
    }

}
