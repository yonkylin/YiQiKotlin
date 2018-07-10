package yonky.yiqikotlin.net.exception

/**
 * Created by Administrator on 2018/7/10.
 */

class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}