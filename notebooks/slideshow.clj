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
;; #### if
;; (if boolean-form then-form optional-else-from)
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