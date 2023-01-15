package com.example.sensimate.screens.myEvents

enum class EventActionOption(val title: String) {
    EditEvent("Edit event"),
    DeleteEvent("Delete event");

    companion object {
        fun getByTitle(title: String): EventActionOption {
            values().forEach { action -> if (title == action.title) return action }

            return EditEvent
        }

        fun getOptions(/*hasEditOption: Boolean*/): List<String> {
            val options = mutableListOf<String>()
            values().forEach { taskAction ->
                if (/*hasEditOption ||*/ taskAction != EditEvent) {
                    options.add(taskAction.title)
                }
            }
            return options
        }
    }
}
