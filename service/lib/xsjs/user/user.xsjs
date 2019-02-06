const Userlib = $.import('xsjs.user', 'user').user;
const userLib = new Userlib(
    $.hdb.getConnection({ treatDateAsUTC: true}),
    "HiMTA::",
    "User"
);

(function () {
    (function handleRequest() {
        try {
            switch ($.request.method) {
                case $.net.http.GET: {
                    let body = $.request.body !== undefined? JSON.parse($.request.body.asString()) : undefined ;
                    userLib.doGet(body);
                    break;
                }
                case $.net.http.PUT : {
                    userLib.doPut(JSON.parse($.request.body.asString()));
                    break;
                }
                case $.net.http.POST : {
                    userLib.doPost(JSON.parse($.request.body.asString()));
                    break;
                }
                case $.net.http.DEL : {
                    userLib.doDelete(JSON.parse($.request.body.asString()));
                    break;
                }
                default: {
                    let body = $.request.body !== undefined? JSON.parse($.request.body.asString()) : undefined ;
                    userLib.doGet(body);
                    break;
                }
            }
        } catch (e) {
            $.response.status = $.net.http.BAD_REQUEST;
            $.response.setBody(e.message);
        }
    }());
}());