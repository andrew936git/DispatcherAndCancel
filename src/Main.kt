import kotlin.random.Random
import kotlinx.coroutines.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() = coroutineScope {
    var personList = mutableListOf<Person>()
    println("Программа работы с базой данных сотрудников")
    delay(1000L)
    println("Добавить сотрудника:\n1.Да\n2.Нет")
    var i = readln().toInt()
    while (i == 1) {
        println("Имя:")
        val name = readln()
        println("Зарплата:")
        val salary = readln().toInt()
        val person = Person(name, salary)
        PersonManager().addPerson(person, personList)
        println("Добавить сотрудника:\n1.Да\n2.Прочитать базу данных")
        i = readln().toInt()
    }

    val job = async(start = CoroutineStart.LAZY) {
        var resultList: Map<Person, Int> = personList.addPassword()
        resultList.readDataPersonList()

    }
    val cancelJob = async(start = CoroutineStart.LAZY) {
        if (i == 0) job.cancelAndJoin()
    }
    cancelJob.await()
    try {
        job.await()
    } catch (e: CancellationException) {
        println("Завершение полной работы")
    }
    cancelJob.cancelAndJoin()
}

suspend fun List<Person>.addPassword(): Map<Person, Int> {
    val passwordMap = mutableMapOf<Person, Int>()
    val rnd = Random
    for (i in this) {
        delay(500L)
        passwordMap.put(i, rnd.nextInt(100000, 999999))
    }
    return passwordMap
}

suspend fun Map<Person, Int>.readDataPersonList() {
    for (i in this) {
        delay(500L)
        println("Сотрудник: ${i.key.name}; пароль: ${i.value}")
    }
}