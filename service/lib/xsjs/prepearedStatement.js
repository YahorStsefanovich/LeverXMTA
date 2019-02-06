class PrepearedStatement {
    static createPreparedInsertStatement(sTableName, oValueObject) {
        let oResult = {
            aParams: [],
            aValues: [],
            sql: "",
        };

        let sColumnList = '', sValueList = '';

        Object.keys(oValueObject).forEach(value => {
            sColumnList += `"${value}",`;
            oResult.aParams.push(value);
        });

        Object.values(oValueObject).forEach(value => {
            sValueList += "?, ";
            oResult.aValues.push(value);
        });

        $.trace.error("svalue " + sValueList);
        $.trace.error("scolumn: " + sColumnList);

        // Remove the last unnecessary comma and blank
        sColumnList = sColumnList.slice(0, -1);
        sValueList = sValueList.slice(0, -2);

        oResult.sql = `insert into "${sTableName}" (${sColumnList}) values (${sValueList})`;

        $.trace.error("sql to insert: " + oResult.sql);
        return oResult;
    }

    static createPreparedSelectStatement(sTableName, oValueObject) {
        //generate query
        let oResult = {
            aParams: [],
            aValues: [],
            sql: "",
        };

        if (oValueObject === undefined){
            oResult.sql += `SELECT * FROM ${sTableName}`;
        } else {
            oResult.sql += "SELECT ";
            for (let key in oValueObject.keys){
                oResult.aParams.push(key);
                oResult.sql += `${key}, `;
            }
            oResult.sql.slice(-2);
        }

        oResult.sql += ` FROM ${sTableName}`;

        return oResult;
    }

    /**
     * delete by id
     * **/
    static createPreparedDeleteStatement(sTableName, oConditionObject) {
        let oResult = {
            aParams: [],
            aValues: [],
            sql: "",
        };

        oResult.sql = `DELETE FROM "${sTableName}" WHERE "usid"=${oConditionObject.usid}`;

        $.trace.error("sql to delete: " + oResult.sql);
        return oResult;
    }
}

module.exports = PrepearedStatement;