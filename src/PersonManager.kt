class PersonManager(){

    fun addPerson(person: Person, list: MutableList<Person>): MutableList<Person> {
        list.add(person)
        return list
    }

}