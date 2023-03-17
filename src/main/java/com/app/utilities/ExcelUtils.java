package com.app.utilities;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

/**
 * General Excel Utilities that using {@link Poiji} and apache POI under the hood to read test data (.xlsx)
 *
 * @author Hendry A.
 * @since 1.0.0
 */

public class ExcelUtils {

    /**
     * Read excel data from given {@code sourceFile} location and map into Java model with specified class {@code typeClass}
     *
     * <br><br>
     * <b>Sample Usage :</b>
     * <pre>
     *     {@code
     *     // map data from MySampleTestData.xlsx into MySamplePOJOModel class
     *     excelUtils.retrieveExcelData(MySamplePOJOModel.class, "testdata/MySampleTestData.xlsx");
     *     }
     * </pre>
     *
     * @param typeClass  target Java class to be mapped into
     * @param sourceFile excel file location
     * @return List of java objects (each row in excel is 1 object)
     */
    public <T> List<T> retrieveRulesExcelData(Class<T> typeClass, String sourceFile, PoijiOptions...poijiOptionalOpts) {
        //todo - handling large excel file ( 1000+ rows ) -- see Consumer class in poiji
        //log.debug("[IAF] Retrieving excel data for model : {}", typeClass.getSimpleName());
        PoijiOptions opts = PoijiOptions.PoijiOptionsBuilder
                .settings() // skip first row after header (header is already skipped by default)
                .preferNullOverDefault(false) // Date,Float,Double,Integer,Long,String will have 'null' value rather than default value.
                .addListDelimiter(";") // default is comma(,)
                .caseInsensitive(true) // used by @ExcelCellName to ignore the case-sensitivity
                .ignoreWhitespaces(true) // used by @ExcelCellName to ignore whitespaces (trim)
                .namedHeaderMandatory(true) // it'll check if @ExcelCellName has corresponding header
                .build();

        // if user passed-in the poijiOptions, use it instead of default one.
        if ( poijiOptionalOpts.length >= 1 ) {
            opts = poijiOptionalOpts[0];
            //log.debug("[IAF] Excel reader is using user-defined POIJI options");
        }

        long debugStartTime = System.nanoTime();
        List<T> data = Poiji.fromExcel(new File(sourceFile), typeClass, opts);
        //log.info("[IAF] Excel reading for model {} took : {} ms", typeClass.getSimpleName(), (System.nanoTime() - debugStartTime) / 1_000_000);
        return data;
    }

}
