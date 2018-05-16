GODAS Viz Platform
==
For yubzhu's thesis

### environment required
* java runtime environment
* internet connected
* put merged GODAS data into misc/GODAS/merged

### cope with NetCDF file in Java
* link: https://www.unidata.ucar.edu/software/thredds/current/netcdf-java/tutorial/

### spring restful service
* link: https://spring.io/guides/gs/rest-service/

### AdminLTE (based on bootstrap 3)
* link: https://github.com/almasaeed2010/AdminLTE

### merged GODAS data
* download 2000-01 ~ 2018-03 data from https://www.esrl.noaa.gov/psd/data/gridded/data.godas.html
* put each dataset into directory respectively, path is /misc/GODAS/****/****.20**.nc
* run NetCDFMergeTest in ncmerge module and merged data will be in /misc/GODAS/merged
* create variables_info.txt in /misc/GODAS/merged/ and write anything you want to show in dashboard home page

### app run command:
* java -jar [jar file] --server.port=[port]

