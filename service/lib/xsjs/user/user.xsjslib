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
            oResult.sql += `SELECT *`;
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

var user = function (connection, prefix, tableName) {

    const PREFIX = prefix;
    const USER_TABLE = prefix + tableName;
    /*
            const USER = $.session.securityContext.userInfo.familyName ?
                $.session.securityContext.userInfo.familyName + " " + $.session.securityContext.userInfo.givenName :
                $.session.getUsername().toLocaleLowerCase(),
    */

    this.doGet = function (obj) {
        const statement = PrepearedStatement.createPreparedSelectStatement(USER_TABLE, obj);
        const result = connection.executeQuery(statement.sql);

        $.response.status = $.net.http.OK;
        $.response.setBody(JSON.stringify(result));
    };


    this.doPost = function (oUser) {

        $.trace.error("oUser:   " + JSON.stringify(oUser));
        //Get Next ID Number
        oUser.usid = getNextval(`${PREFIX}usid`);

        //generate query
        const statement = PrepearedStatement.createPreparedInsertStatement(USER_TABLE, oUser);
        //execute update
        connection.executeUpdate(statement.sql, statement.aValues);

        connection.commit();
        $.response.status = $.net.http.CREATED;
        $.response.setBody(JSON.stringify(oUser));
    };


    this.doPut = function (oUser) {
        //generate query
        let oResult = {
            aParams: [],
            aValues: [],
            sql: "",
        };

        oResult.sql = `UPDATE "${USER_TABLE}" SET "name"='${oUser.name}' WHERE "usid"=${oUser.usid};`;

        $.trace.error("sql to update: " + oResult.sql);

        //execute update
        connection.executeUpdate(oResult.sql, oResult.aValues);

        connection.commit();
        $.response.status = $.net.http.OK;
        $.response.setBody(JSON.stringify(oUser));
    };

    this.doDelete = function (oUser) {
        const statement = PrepearedStatement.createPreparedDeleteStatement(USER_TABLE, oUser);
        connection.executeUpdate(statement.sql, statement.aValues);

        connection.commit();
        $.response.status = $.net.http.OK;
        $.response.setBody(JSON.stringify({}));
    };

    function getNextval(sSeqName) {
        const statement = `select "${sSeqName}".NEXTVAL as "ID" from dummy`;

        const result = connection.executeQuery(statement);

        if (result.length > 0) {
            $.trace.error("ID: "+result[0].ID);
            return result[0].ID;
        } else {
            throw new Error('ID was not generated');
        }
    }

};

