package base

interface TestSteps {

    fun given(given: () -> Unit) {
        given.invoke()
    }

    fun doWhen(doWhen: () -> Unit) {
        doWhen.invoke()
    }

    fun then(then: () -> Unit) {
        then.invoke()
    }
}