/**
 * Copyright 2014-2016 www.fangdd.com All Rights Reserved.
 * Author: chenxiaotian <chenxiaotian@fangdd.com>
 * Date: 2017/2/23
 */
package com.fangdd.traffic.common.mongo.constant;

public class MongoAggregationConsts {

    // Pipeline Aggregation Stages
    public static final String COLL_STATS = "$collStats";
    public static final String PROJECT = "$project";
    public static final String MATCH = "$match";
    public static final String REDACT = "$redact";
    public static final String LIMIT = "$LIMIT";
    public static final String SKIP = "$skip";
    public static final String UNWIND = "$unwind";
    public static final String GROUP = "$group";
    public static final String SAMPLE = "$sample";
    public static final String SORT = "$sort";
    public static final String GEO_NEAR = "$geoNear";
    public static final String LOOKUP = "$lookup";
    public static final String OUT = "$out";
    public static final String INDEX_STATS = "$indexStats";
    public static final String FACET = "$facet";
    public static final String BUCKET = "$bucket";
    public static final String BUCKET_AUTO = "$bucketAuto";
    public static final String SORT_BY_COUNT = "$sortByCount";
    public static final String ADD_FIELDS = "$addFields";
    public static final String REPLACE_ROOT = "$replaceRoot";
    public static final String COUNT = "$count";
    public static final String GRAPH_LOOKUP = "$graphLookup";

    // group accumulator operators
    public static final String SUM = "$sum";
    public static final String AVG = "$avg";
    public static final String FIRST = "$first";
    public static final String LAST = "$last";
    public static final String MAX = "$max";
    public static final String MIN = "$min";
    public static final String PUSH = "$push";
    public static final String ADD_TO_SET = "$addToSet";
    public static final String STD_DEV_POP = "$stdDecPop";
    public static final String STD_DEV_SAMP = "$stdDevSamp";

    // boolean aggregation operators
    public static final String AND = "$and";
    public static final String OR = "$or";
    public static final String NOT = "$not";

    // set operators
    public static final String SET_EQUALS = "$setEquals";
    public static final String SET_INTERSECTION = "$setIntersection";
    public static final String SET_UNION = "$setUnion";
    public static final String SET_DIFFERENCE = "$setDifference";
    public static final String SET_IS_SUBSET = "$setIsSubset";
    public static final String ANY_ELEMENT_TRUE = "$anyElementTrue";
    public static final String ALL_ELEMENTS_TRUE = "$allElementsTrue";

    // comparison aggregation
    public static final String CMP = "$cmp";
    public static final String EQ = "$eq";
    public static final String GT = "$gt";
    public static final String GTE = "$gte";
    public static final String LT = "$lt";
    public static final String LTE = "$lte";
    public static final String NE = "$ne";

    // arithmetic aggregation operators
    public static final String ABS = "$abs";
    public static final String ADD = "$add";
    public static final String CEIL = "$ceil";
    public static final String DIVIDE = "$divide";
    public static final String EXP = "$exp";
    public static final String FLOOR = "$floor";
    public static final String LN = "$ln";
    public static final String LOG = "$log";
    public static final String LOG10 = "$log10";
    public static final String MOD = "$mod";
    public static final String MULTIPLY = "$multiply";
    public static final String POW = "$pow";
    public static final String SQRT = "$sqrt";
    public static final String SUBTRACT = "$subtract";
    public static final String TRUNC = "$trunc";

    // string aggregation operators
    public static final String CONCAT = "$concat";
    public static final String INDEX_OF_BYTES = "$indexOfBytes";
    public static final String INDEX_OF_CP = "$indexOfCP";
    public static final String SPILT = "$spilt";
    public static final String STRCASECMP = "$strcasecmp";
    public static final String STR_LEN_BYTES = "$strLenBytes";
    public static final String STR_LEN_CP = "$strLenCP";
    public static final String SUBSTR = "$substr";
    public static final String SUBSTR_BYTES = "$substrBytes";
    public static final String SUBSTR_CP = "$substrCP";
    public static final String TO_LOWER = "$toLower";
    public static final String TO_UPPER = "$toUpper";

    // text search aggregation
    public static final String META = "$meta";

    // array aggregation operators
    public static final String ARRAY_ELEM_AT = "$arrayElemAt";
    public static final String CONCAT_ARRAYS = "$concatArrays";
    public static final String FILTER = "$filter";
    public static final String INDEX_OF_ARRAY = "$indexOfArray";
    public static final String IS_ARRAY = "$isArray";
    public static final String RANGE = "$range";
    public static final String REVERSE_ARRAY = "$reverseArray";
    public static final String REDUCE = "$reduce";
    public static final String SIZE = "$size";
    public static final String SLICE = "$slice";
    public static final String ZIP = "$zip";
    public static final String IN = "$in";

    // aggragation variable operators
    public static final String MAP = "$map";
    public static final String LET = "$let";

    // aggregation literal operators
    public static final String LITERAL = "$literal";

    // date aggregation operators
    public static final String DAY_OF_YEAR = "$dayOfYear";
    public static final String DAY_OF_MONTH = "$dayOfMonth";
    public static final String DAY_OF_WEEK = "$dayOfWeek";
    public static final String YEAR = "$year";
    public static final String MONTH = "$month";
    public static final String WEEK = "$week";
    public static final String HOUR = "$hour";
    public static final String MINUTE = "$minute";
    public static final String SECOND = "$second";
    public static final String MILLISECOND = "$millisecond";
    public static final String DATE_TO_STRING = "$dateToString";
    public static final String ISO_DAY_OF_WEEK = "$isoDayOfWeek";
    public static final String ISO_WEEK = "$isoWeek";
    public static final String ISO_WEEK_YEAR = "$isoWeekYear";

    // conditional aggregation operators
    public static final String COND = "$cond";
    public static final String IF_NULL = "$ifNull";
    public static final String SWITCH = "$switch";

    // data type aggregation operators
    public static final String TYPE = "$type";


}
