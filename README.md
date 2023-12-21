### Prerequisites

* java jdk version 11

### Installation

Clone the repo
   ```sh
   git clone https://github.com/RedMage22/CsvComparison.git
   ```

### Usage

- Once the repo is cloned, you can start the app in your editor of choice.
  - using IntelliJ:
    - go to the gradle pane. Under CsvComparison click on Tasks -> application -> run.
    - alternatively you can run the application from the `CsvComparisonApp.java` file.
  - using terminal or command line, run the `CsvComparisonApp.java` file with the java command.
- The app has two file buttons, `File 1` and `File 2` that will only open `csv` files.
- The third button, `output`, can manually be set the output destination or will automatically update the output destination to the directory of the last file opened. 
- The `process` button starts the comparison process.
- The app assumes that:
  - the first column of each file has unique values to allow for successful comparisons.
  - both files have all the necessary columns present
  - columns are in the same order with the same value types.
- Once the comparison process is completes, a pop-up notification will appear.
- If the app experiences an 
- The app creates an output file that contains rows of the compared results next to each other.
  - each column of the original record is duplicated and suffixed with _a and _b, respectively, to
    distinguish between File 1 and File 2 data.
- An `Is Equal?` boolean value is appended to the end of each record comparison to conveniently show record equality. (TRUE or FALSE)
- If a record found in either file is not present in the other file, the relevant column value for that record will be marked with `no data` and will
  automatically fail the comparison check - The `Is Equal?` value will be FALSE.
- At the bottom of the newly created output file you will find a record summary highlighting:
    - total record counts in both files
    - total number of failed comparisons
    - total number of records not found in both files
