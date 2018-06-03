package com.scabher.restaurant.model

object Tables {

    private val tables: List<Table> = listOf(
        Table(0, emptyList(), "Mesa 0", "Mesa pegada al lado de caja"),
        Table(1, emptyList(), "Mesa 1", "Mesa pegada a la entrada"),
        Table(2, emptyList(), "Mesa 2", "1ª mesa en la fila de la izquierda"),
        Table(3, emptyList(), "Mesa 3", "2ª mesa en la fila de la izquierda"),
        Table(4, emptyList(), "Mesa 4", "3ª mesa en la fila de la izquierda"),
        Table(5, emptyList(), "Mesa 5", "1ª mesa en la fila de la derecha"),
        Table(6, emptyList(), "Mesa 6", "2ª mesa en la fila de la derecha"),
        Table(7, emptyList(), "Mesa 7", "3ª mesa en la fila de la derecha")
    )

    fun getTables() : List<Table> {
        return tables
    }

    fun getTableById(tableId: Int): Table? {
        return tables.find { table: Table -> table.id == tableId }
    }

    fun addOrderToTable(tableId: Int, order: Order) {
        var table = getTableById(tableId)

        if (table == null) {
            return
        }

        table.addOrder(order)
    }

    fun removeOrderToTable(tableId: Int, order: Order) {
        var table = getTableById(tableId)

        if (table == null) {
            return
        }

        table.removeOrder(order)
    }

    fun get(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    val count
        get() = tables.size
}