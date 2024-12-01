^{::clerk/visibility {:code :hide :result :hide}}
(ns slideshow
	(:require [nextjournal.clerk :as clerk]
			  [nextjournal.clerk-slideshow :as slideshow])
	)

{::clerk/visibility {:code :hide :result :show}}
(clerk/html [:div.flex.items-center.overflow-hidden.gap-4
			 [:img.h-12.my-0 {:src "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Clojure_logo.svg/800px-Clojure_logo.svg.png"}]
			 [:h1 "Clojure"]])
(clerk/html [:h4.absolute.top-60 "Francisco Zizzi"])

;; ---
;; # Brief History
;; - Released in 2007
;; - Created by Rich Hickey
;; - "I wanted a language as acceptable as Java or C#, put supporting a much simpler programming model, to use for the kinds of information system development I had been doing professionally"
;; ---

;; # Main Features
;; - Functional
;; - Dynamic
;; - Homoiconic
;; - Immutability
;; - Great with concurrency and data processing/manipulation
;; - Live programming
;; - Runs on the JVM and can be compiled to Javascript

;; ---
;; # Syntax
;; ### The syntax consists of:
;; #### Symbols
;; - . + - * / : etc.
;; #### Literals, mainly:
{::clerk/visibility {:code :show :result :hide}}
{
 :string          "Hello World!"
 :integer         985
 :floating-point  3.14
 :ratio           15/4
 	; Used to reduce floating point error
 :bool            [true, false]
 :keyword         :my-keyword
 	; Could be thought of as enums
 	; Useful as keys for maps
 :nil             nil
 	; Represents the Java null
 }
;; #### Data Structures
{
 :list            '(1 2 "Three")
 	; Accessed sequentially
 :vector          [1 :two "Three"]
 	; Random access
 :map             {:key1 "val1" :key2 2}
 :set             #{:one 2 "Three" 4.5}
 }
;; Notice how no commas are needed

;; ---
;; # Evaluation
;; Literals evaluate themselves:
{::clerk/visibility {:code :show :result :show}}
"Hello!"
;; Clojure is **homoiconic**, which basically means that it's written with its own data structures.

;; Non-empty lists are considered _calls_. The first element is evaluated as a function call:
(+ 1 2 3)
;; If you don't want to evaluate the contents of a list, use ':
'(+ 1 2 3)

;; ---
;; # Boolean Expressions
;; #### Equality
(= #{1 2 3} #{3 2 1})
(= true false)
;; #### Or
;; Returns the first true value or the last value if no true values are found
(or false nil "Hello! :)" true)
(or false false nil)
;; #### And
;; Returns the first false value or the last value if no false values are found
(and 1 2 3 4 nil 5)
(and 1 2 3 4)
;; nil and false are both false values, everything else is considered true

;; ---
;; # Control Flow
;; (if boolean-form
;; 		then-form
;; 		optional-else-from)
(if 4
	"True!"
	"False :(")
(if (and :val nil)
	"True!"
	"False :(")
;; Use the _do_ operator to do multiple things in an if form
(if true
	(do (println "Hey") "Hey"))
;; _when_ does the same thing, but it does not accept an else form
(when true
	(println "Something")
	(println "Something else")
	[1 2 3])

;; ---
;; # def and let
;; _def_ is used to assign a name to a value and adds it to the global context
(def my-clojure-vector [1 2 3 4])
(str "This is MY vector -> " my-clojure-vector) ; str concatenates strings, also turns values into strings automatically
;; _let_ is also used to assign a name to a value but just in a local context, only available within the parentheses of _let_
(let [a 5 b 6 c 2]
	[a b c])
;; Or also:
(let [[a b c] [1 2 (reduce + (take 10 (range)))]]
	[a b c])

;; ---
;; # Functions
;; We can create a function with _defn_
{::clerk/visibility {:result :hide}}
(defn my-clojure-function "Optional docstring" [arg1 arg2]
	(str arg1 arg2))
{::clerk/visibility {:result :show}}
(my-clojure-function "Hello " "world!")

;; It is possible to define functions with an undefined amount of arguments
{::clerk/visibility {:result :hide}}
(defn average [& args]
	(/ (apply + args) (count args)))

{::clerk/visibility {:result :show}}
(average 0 0 1)
;; We can also define different behaviours depending on the _arity_ (number of arguments received)
{::clerk/visibility {:result :hide}}
(defn multi-arity
	([arg1 arg2 arg3] "Three")
	([arg1 arg2] "Two")
	([arg1] "One")
	)
{::clerk/visibility {:result :show}}
[(multi-arity 1) (multi-arity 1 2) (multi-arity 1 2 3)]

;; ---
;; # Higher Order Functions
;; ### Function Literals
;; We can define anonymous functions using _fn_
{::clerk/visibility {:result :hide}}
(def bands [
			   {:name "Brown Beaters"   :genre :rock}
			   {:name "Sunday Sunshine" :genre :blues}
			   {:name "Foolish Beaters" :genre :rock}
			   {:name "Monday Blues"    :genre :blues}
			   {:name "Friday Fewer"    :genre :blues}
			   {:name "Saturday Stars"  :genre :jazz}
			   {:name "Sunday Brunch"   :genre :jazz}
			   ])
{::clerk/visibility {:result :show}}
(def rock-bands
	(filter
	 (fn [band] (= :rock (:genre band)))
	 bands))
;; Or we can use #. % represents an argument, in this case a band
(def rock-bands (filter #(= :rock (:genre %)) bands))
;; It supports multiple arguments:
(#(str %1 %2 %3) 1 2 3)
;; ### Functions returning functions and functions as values
;; Adder returns a function which we use to define add-five
{::clerk/visibility {:result :hide}}
(defn adder [x]
	(fn [a] (+ x a)))
(def add-five (adder 5))

{::clerk/visibility {:result :show}}
(add-five 100)

;; ---
;; # Destructuring
;; We can use destructuring to easily access values in data structures
{::clerk/visibility {:result :hide}}
(defn some-function [ [first second third & the-rest] ]
	(str "First: " first "\nSecond: " second "\nThird: " third "\nThe rest: " the-rest))
{::clerk/visibility {:result :show}}
(some-function [1 2 3 2 5 12 3])
{::clerk/visibility {:result :hide}}
;; Destructuring a map:
(defn some-function [{name :name surname :surname}]
	(str "Hello, " name " " surname))
(defn some-other-function [{name :name surname :surname :as person}]; We can use the :as keyword to get the map too
	(str name " " surname " lives in " (:city person)))
{::clerk/visibility {:result :show}}
(some-function {:name "John" :surname "Doe"})
(some-other-function {:name "John" :surname "Doe" :city "Venice"})

;; ---
;; # Polymorphism
;; TODO

;; ---
;; # Macros
;; TODO

;; ---
;; # The REPL
;; TODO
