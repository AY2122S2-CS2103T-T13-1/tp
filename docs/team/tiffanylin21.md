---
layout: page
title: Tiffany's Project Portfolio Page
---

### Project: WoofAreYou

WoofAreYou is a desktop app for pet daycare owners to handle the administrative information of their pets. If you can type fast, WoofAreYou can get your contact management tasks done faster than traditional GUI apps. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:

* **Contributions to UG**:
  1. Updated UG to include `time` and `delete` commands [#41](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/41)
  2. Updated UG for `charge` command [#112](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/112)
* **Contributions to DG**:
  1. Updated diagrams, images and content to reflect refactoring from AB3 to WoofAreYou. Update SortCommand section to reflect additional valid sort fields. [#192](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/192)
  2. Worked on content for `charge` command [#84](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/84)
* **Contributions to other documentation**:
    1. Updated index.md with relevant links to our project. [#60](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/60) [#198](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/198)
* **New Feature**: Charge
    1. **Charge Command** [#103](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/103)
    * What it does:
        * Allows the user to tabulate monthly charge of pet.
        * Charge requires user to specify a month and cost per day.
    * Justification:
        * This feature enables the user to calculate cost to charge a pet for a specified month. This cost is calculated based on the pet's attendance during the specified month.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=tiffanylin21&breakdown=true)
* **Project management**:
    1. Reviewed pull requests by teammates
    2. Helped in opening and assigning issues
* **Enhancements to existing features**:
  1. Refactoring [#191](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/191) [#61](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/61)
     * Refactored `Person` in AB3 with its attributes to `Pet` with its initial attributes `Name`, `OwnerName`, `Phone`, `Address` and `Tag`. 
     * Update testcases to check for `OwnerName` and deleted ones for `Email` attribute. 
  2. Bug Fix: Error Messages  [#176](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/176)
     * Fixed inconsistent error messages given by AB3 when user inputs an invalid index. Error message given when index is out-of-bounds and when index is not a positive non-zero integer are different.
  3. Bug Fix: Duplicate Whitespaces [#77](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/77)
     * Fixed duplicate whitespace error in AB3 naming. AB3 treat names containing duplicate whitespaces as unique. 
     * Fixed to automatically trim duplicate whitespaces in `Name`, `OwnerName`, `Diet`, `Address` and `Tag` description. 
  4. Message Enhancement [#171](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/171)
     * Improved success message given when `charge`command is run. 
     * Initially, only pet name, amount chargeable and month is displayed.
     * Updated to display year and the pet's present attendance dates in the specified month.
* **Community**:
    * PRs reviewed (with non-trivial review comments):[#80 comment 1](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80#discussion_r832306218) [#80 comment 2](https://github.com/AY2122S2-CS2103T-T13-1/tp/pull/80#discussion_r832341447)
    * Reported bugs and suggestions for peers during PE-Dry Run
      * [Duplicate whitespaces allowed in names and considered unique by NUSocials](https://github.com/Tiffanylin21/ped/issues/1)
      * [Addresses can't be seen on GUI when too long](https://github.com/Tiffanylin21/ped/issues/3)



