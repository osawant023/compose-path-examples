package com.jetpackcompose.compose_path_android

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


/*

Input: nums = [4,3,2,7,8,2,3,1]
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

find the missing element from the list
find the max value from the above list & list down missing element
write sort algo except bubble
find the missing element from the sorted list

*/

//fun main() {
//    input(arrayOf(4, 3, 2, 7, 8, 2, 3))
//}


fun input(array: Array<Int>) {
    val missingNum = mutableListOf<Int>()
    var maxElement = array[0]
    var minElement = array[0]

    for ( i in 0..array.lastIndex) {
        if (array[i] > maxElement) maxElement = array[i]
        else if (array[i] < minElement) minElement = array[i]
    }

    for ( i in minElement..maxElement) {
        if(!array.contains(i)) missingNum.add(i)
    }

    println("max no $maxElement ,min no $minElement")
    println("MISSING NUM $missingNum")
}

data class User(val name: String , val lastName: String)
class UserSample{
    lateinit var name : String
    constructor(name: String){
        this.name = name
    }
}

fun main(){
    val u1 = User("vipul","goyal")
    val u2 = User("vipul","goyal")

    val (lastName,name) = u1

    println("destructring $lastName $name")
    println("u1 == u2 : ${u1 == u2}")

    val s1 = UserSample("vipul")
    val s2 = UserSample("vipul")

    println("s1 == s2 : ${s1 == s2}")
}













