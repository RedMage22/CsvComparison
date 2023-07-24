### Prerequisites

* java jdk version 11

### Installation

Clone the repo
   ```sh
   git clone https://github.com/RedMage22/CsvComparison.git
   ```

### Usage

- Once the repo is cloned, you can start the app by going to the gradle pane in IntelliJ. Under CsvComparison click on Tasks -> application -> run.
    - Alternatively you can run the `CsvComparisonApp.java` file.
- The app has two file buttons, `control` and `sample` that will look for and use `csv` files.
- The third button, `output`, can manually be set or will automatically update to the directory of the last file opened. 
- The `process` button starts the comparison process.
- The app uses the control file’s 1st column values to lookup records in the sample file.
- It creates an output file that contains the compared results where each column of the record is duplicated and suffixed with _a and _b respectively to
  distinguish between control and sample data.
- An `Is Equal?` boolean value is appended to the end of each record comparison to conveniently show record equality. (TRUE or FALSE)
- If a record found in the control file is not present in the sample file the “_b” column values will be marked with `no value` and will
  automatically fail the comparison check - `Is Equal?` value will be FALSE.
- At the bottom of the newly created output file you will find a record summary highlighting:
    - total record counts in the control and sample files
    - differences found in the sample file
    - missing records