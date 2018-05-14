package yubzhu.controller;

import yubzhu.exception.AggregateDimensionNotFoundException;
import yubzhu.exception.AggregateMethodNotFoundException;
import yubzhu.exception.ApplicationException;
import yubzhu.exception.DatasetNotFoundException;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 朱宇斌 on 2018/5/11
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/godas/stat/")
public class GodasStatisticController {

    @GetMapping("/3d")
    JSONArray query3dAggregatedDataset (@RequestParam(value = "dataset") String dataset,
                                     @RequestParam(value = "time") String time,
                                     @RequestParam(value = "aggr_method") String aggregateMethod,
                                     @RequestParam(value = "aggr_dim") String aggregateDimension,
                                     @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                     @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                     @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                     @RequestParam(value = "to_lon", defaultValue = "359.5") float toLon) throws ApplicationException {
        JSONArray jsonArrayDataset;
        if (dataset.equals("thflx")) {
            jsonArrayDataset = new GodasController().queryTotalDownwardHeatFlux(time, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("sltfl")) {
            jsonArrayDataset = new GodasController().querySaltFlux(time, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("sshg")) {
            jsonArrayDataset = new GodasController().querySeaSurfaceHeight(time, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("dbss_obil")) {
            jsonArrayDataset = new GodasController().queryGeometricDepthBelowSeaSurfaceIsothermalLayer(time, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("dbss_obml")) {
            jsonArrayDataset = new GodasController().queryGeometricDepthBelowSeaSurfaceMixingLayer(time, fromLat, toLat, fromLon, toLon);
        } else {
            throw new DatasetNotFoundException();
        }
        JSONArray jsonArrayAggregated = new JSONArray();
        if (aggregateDimension.equals("lat")) {
            if (aggregateMethod.equals("max")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double maxVal = Double.MIN_VALUE;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        maxVal = Math.max(maxVal, (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2));
                    }
                    if (maxVal == Double.MIN_VALUE) {
                        maxVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    jsonArray.put(maxVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("min")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double minVal = Double.MAX_VALUE;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        minVal = Math.min(minVal, (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2));
                    }
                    if (minVal == Double.MAX_VALUE) {
                        minVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    jsonArray.put(minVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("avg")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double sumVal = 0;
                    int count = 0;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        sumVal += (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2);
                        count += 1;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    if (count == 0) {
                        jsonArray.put(0);
                    } else {
                        jsonArray.put(sumVal / count);
                    }
                    jsonArrayAggregated.put(jsonArray);
                }
            } else {
                throw new AggregateMethodNotFoundException();
            }
        } else if (aggregateDimension.equals("lon")) {
            if (aggregateMethod.equals("max")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double maxVal = Double.MIN_VALUE;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        maxVal = Math.max(maxVal, (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2));
                    }
                    if (maxVal == Double.MIN_VALUE) {
                        maxVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    jsonArray.put(maxVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("min")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double minVal = Double.MAX_VALUE;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        minVal = Math.min(minVal, (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2));
                    }
                    if (minVal == Double.MAX_VALUE) {
                        minVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    jsonArray.put(minVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("avg")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double sumVal = 0;
                    int count = 0;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        sumVal += (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2);
                        count += 1;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    if (count == 0) {
                        jsonArray.put(0);
                    } else {
                        jsonArray.put(sumVal / count);
                    }
                    jsonArrayAggregated.put(jsonArray);
                }
            } else {
                throw new AggregateMethodNotFoundException();
            }
        } else {
            throw new AggregateDimensionNotFoundException();
        }

        return jsonArrayAggregated;
    }

    @GetMapping("/4d")
    JSONArray query4dAggregatedDataset (@RequestParam(value = "dataset") String dataset,
                                      @RequestParam(value = "time") String time,
                                      @RequestParam(value = "level") int level,
                                      @RequestParam(value = "aggr_method") String aggregateMethod,
                                      @RequestParam(value = "aggr_dim") String aggregateDimension,
                                      @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                      @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                      @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                      @RequestParam(value = "to_lon", defaultValue = "359.5") float toLon) throws ApplicationException {
        JSONArray jsonArrayDataset;
        if (dataset.equals("salt")) {
            jsonArrayDataset = new GodasController().querySalinity(time, level, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("dzdt")) {
            jsonArrayDataset = new GodasController().queryGeometricVerticalVelocity(time, level, fromLat, toLat, fromLon, toLon);
        } else if (dataset.equals("pottmp")) {
            jsonArrayDataset = new GodasController().queryPotentialTemperature(time, level, fromLat, toLat, fromLon, toLon);
        } else {
            throw new DatasetNotFoundException();
        }
        JSONArray jsonArrayAggregated = new JSONArray();
        if (aggregateDimension.equals("lat")) {
            if (aggregateMethod.equals("max")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double maxVal = Double.MIN_VALUE;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        maxVal = Math.max(maxVal, (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2));
                    }
                    if (maxVal == Double.MIN_VALUE) {
                        maxVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    jsonArray.put(maxVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("min")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double minVal = Double.MAX_VALUE;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        minVal = Math.min(minVal, (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2));
                    }
                    if (minVal == Double.MAX_VALUE) {
                        minVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    jsonArray.put(minVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("avg")) {
                for (int i = 0; i < jsonArrayDataset.length(); i += toLon - fromLon + 1) {
                    double sumVal = 0;
                    int count = 0;
                    for (int j = 0; j < toLon - fromLon + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(i + j)).get(2) == 0) {
                            continue;
                        }
                        sumVal += (double)((JSONArray)jsonArrayDataset.get(i + j)).get(2);
                        count += 1;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(1));
                    if (count == 0) {
                        jsonArray.put(0);
                    } else {
                        jsonArray.put(sumVal / count);
                    }
                    jsonArrayAggregated.put(jsonArray);
                }
            } else {
                throw new AggregateMethodNotFoundException();
            }
        } else if (aggregateDimension.equals("lon")) {
            if (aggregateMethod.equals("max")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double maxVal = Double.MIN_VALUE;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        maxVal = Math.max(maxVal, (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2));
                    }
                    if (maxVal == Double.MIN_VALUE) {
                        maxVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    jsonArray.put(maxVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("min")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double minVal = Double.MAX_VALUE;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        minVal = Math.min(minVal, (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2));
                    }
                    if (minVal == Double.MAX_VALUE) {
                        minVal = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    jsonArray.put(minVal);
                    jsonArrayAggregated.put(jsonArray);
                }
            } else if (aggregateMethod.equals("avg")) {
                for (int i = 0; i < toLon - fromLon + 1; i += 1) {
                    double sumVal = 0;
                    int count = 0;
                    for (int j = 0; j < (toLat - fromLat) * 3 + 1; j += 1) {
                        if ((double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2) == 0) {
                            continue;
                        }
                        sumVal += (double)((JSONArray)jsonArrayDataset.get(j * (int)(toLon - fromLon + 1) + i)).get(2);
                        count += 1;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(((JSONArray)jsonArrayDataset.get(i)).get(0));
                    if (count == 0) {
                        jsonArray.put(0);
                    } else {
                        jsonArray.put(sumVal / count);
                    }
                    jsonArrayAggregated.put(jsonArray);
                }
            } else {
                throw new AggregateMethodNotFoundException();
            }
        } else {
            throw new AggregateDimensionNotFoundException();
        }

        return jsonArrayAggregated;
    }
}
