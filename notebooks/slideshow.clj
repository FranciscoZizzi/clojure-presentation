^{::clerk/visibility {:code :hide :result :hide}}
(ns slideshow
	(:require [nextjournal.clerk :as clerk]
			  [nextjournal.clerk-slideshow :as slideshow])
	)
{::clerk/visibility {:code :hide :result :hide}}
(clerk/add-viewers! [slideshow/viewer])

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
;; * Functional
;; * Dynamic
;; * Homoiconic
;;     - Clojure programs are represented by Clojure data structures.
;; * Immutability
;; * Great with concurrency and data processing/manipulation
;; * Live programming through the REPL
;; * Runs on the JVM
;;     - Compiles to a .jar file, so anyone with Java installed can run it.
;; * Can compile to Javascript
;;     - Makes frontend development with Clojure easier through React wrappers, for example.

;; ---
;; # The REPL
;; The REPL (Read-Eval Print Loop) is a programming environment which enables the programmer to interact with a running
;; Clojure program and modify it, evaluating one code expression at a time.\
;; \
;; This enables a quick feedback loop not available in most other languages.\
;; \
;; We can think of the REPL as something similar to SSH, in the same way you can interact with a remote server, we can use the REPL to
;; interact with a running Clojure process.
{::clerk/visibility {:code :hide :result :show}}
(clerk/html [:img {:src "https://clojure.org/images/content/guides/repl/show-terminal-repl.gif"}])

;; ---
;; # Syntax
;; The syntax consists of:
;; #### Numeric types
{::clerk/visibility {:code :show :result :hide}}
985 			; Integer
3.14			; Floating Point
1/3				; Ratio
;; #### Character types
"Hello World!"	; String
\e				; Character
[#"[0-9]+"] 	; Regular expression (without the [])
;; #### Symbols and idents
map				; Symbol
nil 			; Null value
[true false] 	; Booleans
:hello			; Keyword
:some/thing		; Keyword with namespace
;; #### Literal collections
'(1 2 "Three")			; List
[1 :two "Three"]		; Vector
{:key1 "val1" :key2 2}	; Map
#{:one 2 "Three" 4.5}	; Set

;; ---
;; # Evaluation
;; Clojure reads and evaluates expressions differently.\
;; In java source code is read as characters by the compiler, which produces bytecode which can be loaded by the JVM:
{::clerk/visibility {:code :hide :result :show}}
(clerk/html [:img {:src "https://www.clojure.org/images/content/guides/learn/syntax/traditional-evaluation.png"}])
;; In Clojure, source code is read as characters by the Reader (This separation between the reader and compiler is what
;; makes room for macros, which we'll see later). The reader can read from a .clj file or from
;; Expressions received interactively through the REPL. The reader produces Clojure data that the compiler then uses
;; to produce the bytecode for the JVM:
(clerk/html [:img {:src "https://www.clojure.org/images/content/guides/learn/syntax/clojure-evaluation.png"}])
;; A key difference is the compilation unit: In Java it is a class or .java file, while in Clojure it is an expression.
;; Files in Clojure are read as series of expressions, you could type each expression from a file into the REPL and you
;; would get the same result.

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
;; nil and false are considered false values, everything else is considered true

;; ---
;; # Control Flow
;; (if boolean-form\
;; 		then-form\
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
;; _when_ does the same thing as if and do, but does not accept an else form
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
;; Clojure supports runtime polymorphism with **multimethods**\
;; Multimethods are defined using defmulti, which takes qhte name of the multimethod and the dispatch function.
{::clerk/visibility {:result :hide}}
(defmulti encounter (fn [x y] [(:Species x) (:Species y)])) ; the dispatch function returns the species of x and y
;; Each method is then implemented using defmethod, passing the name, dispatch value and function body.
(defmethod encounter [:Bunny :Lion] [b l] :run-away)
(defmethod encounter [:Lion :Bunny] [l b] :eat)
(defmethod encounter [:Lion :Lion] [l1 l2] :fight)
(defmethod encounter [:Bunny :Bunny] [b1 b2] :mate)
(def b1 {:Species :Bunny :other :stuff})
(def b2 {:Species :Bunny :other :stuff})
(def l1 {:Species :Lion :other :stuff})
(def l2 {:Species :Lion :other :stuff})
{::clerk/visibility {:result :show}}
[ (encounter b1 b2)
(encounter b1 l1)
(encounter l1 b1)
(encounter l1 l2) ]
;; This would be equivalent to
{::clerk/visibility {:result :hide}}
(defn encounter [x y]
	(cond
		(and (= :Bunny (:Species x)) (= :Lion (:Species y)))
		:run-away
		(and (= :Lion (:Species x)) (= :Bunny (:Species y)))
		:eat
		(and (= :Lion (:Species x)) (= :Lion (:Species y)))
		:fight
		(and (= :Bunny (:Species x)) (= :Bunny (:Species y)))
		:mate
		)
	)

;; ---
;; # Macros
;; We saw that clojure is homoiconic, so expressions are written things in terms of lists.
;; This means we are able to manupulate those lists, after all, they are just another data structure.\
;; So I could make a function that reverses the expression given, like so:
{::clerk/visibility {:result :hide}}
(defn reverse-form-1 [form]
	(reverse form)
	)
{::clerk/visibility {:result :show}}
(reverse-form-1 '(1 2 3 str))
;; And then evaluate it:
(eval (reverse-form-1 '(1 2 3 str)))
;; Problem: We need to provide the form using a single quote (') and use _eval_ to evaluate it.\
;; Solution: We use a macro.
{::clerk/visibility {:result :hide}}
(defmacro reverse-form [form]
	(reverse form))
{::clerk/visibility {:result :show}}
;; ```
;; (reverse-form (1 2 3 str))
;; => "321"
;; ```
;; Macros allow us to extend the language itself by writing code that generates other code at compile time.\
;; We can use _macroexpand_ to see how the code would look like when expanded by the compiler.
(macroexpand '(when true "Hello"))
;; We can see that _when_ is actually just a macro, that joins if and do.

