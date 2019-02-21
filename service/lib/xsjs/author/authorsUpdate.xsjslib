var JsonFormatter = $.import('xsjs', 'JsonFormatter').formatter;

function authorsUpdate(param) {
    //get table name
    var after = param.afterTableName;

    var pStmt = param.connection.prepareStatement("select * from \"" + after + "\"");
    var oResult = pStmt.executeQuery();

    var oAuhtorItems = JsonFormatter.recordSetToJSON(oResult, "items");
    var oAuthor = oAuhtorItems.items[0];
    $.trace.error(JSON.stringify(oAuthor));

    var uStmt;
    var date = new Date();
    // uStmt = param.connection.prepareStatement(`UPDATE "${after}" SET "name"='${oAuthor.name}', "updated"='${date}' WHERE "author_id"='${oAuthor.author_id}'`);
    uStmt = param.connection.prepareStatement(`UPDATE "${after}" SET "name"=?, "updated"=? WHERE "author_id"=?`);
    pStmt.setString(1, oAuthor.name.toString());
    pStmt.setDate(2, date);
    pStmt.setDate(3, oAuthor.author_id.toString());
    uStmt.executeUpdate();
}