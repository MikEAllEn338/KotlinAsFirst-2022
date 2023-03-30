@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IllegalArgumentException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0)
        throw IllegalArgumentException()
    return MatrixImpl<E>(height, width, e)
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val matrix = mutableMapOf<Cell, E>()

    init {
        for (i in 0 until height) {
            for (j in 0 until width) {
                matrix.put(Cell(i, j), e)
            }
        }
    }

    override fun get(row: Int, column: Int): E {
        return matrix.get(Cell(row, column))!!
    }

    override fun get(cell: Cell): E {
        return matrix.get(cell)!!
    }

    override fun set(row: Int, column: Int, value: E) {
        matrix.put(Cell(row, column), value)
    }

    override fun set(cell: Cell, value: E) {
        matrix.put(cell, value)
    }

    override fun equals(other: Any?): Boolean {
        return other is MatrixImpl<*> &&
                height == other.height &&
                width == other.width && matrix.equals(other.matrix)
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (i in 0 until height) {
            for (j in 0 until width) {
                result.append(matrix.get(Cell(i, j)).toString())
                result.append(" ")
            }
            result.append("\n")
        }
        return result.toString()
    }
}

