package ru.otus.cars

fun main() {
    GasStation.fuelUpCars(modelForFuel())
    println("\n===> drive cars...")
    driveCars()
    println("\n===> inner test...")
    innerNestedCheck()
    println("\n===> garage make...")
    garageMake()
    println("\n===> model special...")
    modelSpecial()
    println("\n===> model make...")
    modelMake()

}

fun modelForFuel(): List<Car>{
    return listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78)),
        Taz
    )
}

fun driveCars() {
    val vaz1 = Togliatti.buildCar(Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Car.Plates("321", 78))

    println("Экземпляры класса имеют разное внутреннее состояние:")
    vaz1.wheelToRight(10)
    println(vaz1.toString()) // Выводит 10 и случайную скорость
    vaz2.wheelToLeft(20)
    println(vaz2.toString()) // Выводит -20 и случайную скорость
}

fun innerNestedCheck() {
    val vaz = Vaz2107.build(Car.Plates("123", 77))
    val output = vaz.VazOutput() // Создаем новый объект ИЗ ЭКЗЕМПЛЯРА МАШИНЫ

    println("Скорость до проверки: ${output.getCurrentSpeed()}") // Выводит 0
    Vaz2107.test(vaz) // Газуем...
    println("Скорость после проверки: ${output.getCurrentSpeed()}") // Выводит случайную скорость
}

fun garageMake() {
    val maker = "Дядя Вася"
    val garage = object : CarFactory {
        override fun buildCar(plates: Car.Plates): Car {
            println("Запил Жигулей у: $maker...")
            println("Машину не проверяем... и в продакшн...")
            return Vaz2107.build(plates)
        }
    }

    val vaz = garage.buildCar(Car.Plates("500", 50))
    println(vaz.toString())
}

fun modelSpecial() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78)),
        Taz
    )

    cars.forEach { car ->
        when(car) {
            is Vaz2107 -> car.drdrdrdrdr()
            is Vaz2108 -> car.zhzhzhzh()
            Taz -> println("Таз больше не ездит!")
        }
    }
}

fun modelMake() {
    val vaz1 = Togliatti.buildCar(Vaz2107, Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Vaz2108, Car.Plates("321", 78))

    println("Создали машины:")
    println(vaz1.toString()) // 2107
    println(vaz2.toString()) // 2108
}

object GasStation {
    fun fuelUpCars(cars: List<Car>) {
        try {
            cars.forEachIndexed { index, car ->
                val fuelAmount = when (car.tankMouth) {
                    is LpgMouth -> 100
                    is PetrolMouth -> 250
                    else -> throw IllegalArgumentException("Неверный тип бака.")
                }
                car.tankMouth.fuelUp(fuelAmount)
                println(car)
            }
        } catch (e: IllegalArgumentException) {
            println("Неверный тип бака.: ${e.message}")
        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}
