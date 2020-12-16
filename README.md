# CenusAnalyserProblem

UC1:Ability for the analyser to load the Indian States Census Information from a csv file

UC2:Ability for the analyser to load the Indian States Code Information from a csv file

RF1A:Refactor the code as DRY was violated to extract data from OpenCSV

RF1B: Refactor the code as DRYwas violated to get count from CSV Iterator

RF2: Refactored Code to ensureSingle Responsibility Principle (SRV) for Analyser and use Delegation Principle to handle CSV Files

RF3: Refactored Code and Introduced CSV Builder and Factory to ensure Programming for Interface then Implementation

RF4: A)Refactored the code to Introduce CSV Exception to ensure CSV Builder encapsulate all changes. B)Refactored the code to create a separate jar and include the jar in the Census Analyser Project. C)As we have used OpenCSV Library there is also Commons CSV Library. Also implement Commons CSV.

RF5: Refactored Code to take in List of Census Data instead of Iterating through the File

UC3: Ability for Analyser to report the State Census Data in a Json Format according to State alphabetical order

UC4: Ability for Analyser to report the State code Data in a Json Format according to State Code

UC5: Ability for Analyser to report the State Census Data in a Json Format from most populous state to the least one

UC6: Ability for Analyser to report the State Census Data in a Json Format from most population density state to the least one

UC7: Ability for Analyser to report the State Census Data in a Json Format from Largest State by Area to the smallest state - Write the Sorted Output into a


