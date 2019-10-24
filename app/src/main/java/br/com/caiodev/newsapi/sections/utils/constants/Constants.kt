package br.com.caiodev.newsapi.sections.utils.constants

object Constants {

    //Success
    const val successWithoutBody = 2

    //Error
    //Exceptions
    const val unknownHostException = 3
    const val socketTimeoutException = 4
    const val sslHandshakeException = 5
    const val genericException = 6

    //HTTP codes
    const val clientSideError = 7
    const val serverSideError = 8
    const val forbidden = 9

    //Generic
    const val genericError = 10
}