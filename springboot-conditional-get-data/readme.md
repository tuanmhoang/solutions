# Contents
- [Problem](#problem) 
- [Solution 1](#solution-1)
- [Solution 2](#solution-2)
- [Solution 3](#solution-3)
- [Solution 4](#solution-4)

## Problem

For now, need a dynamic solution for returning the type. Example

```
public class Information {
	// General information
	private String fullName;
	private String dob;
	private String nationality;
	
	// Legal information
	private String passportNo;
	private String dateOfIssue;
	
	// Contact information
	private String address;
	private String phoneNo;
}
```

When a user choose type
- `general`, the returned data should be `fullName, dob, nationality`
- `legal`, the returned data should be `passportNo, dateOfIssue`
- `contact`, the returned data should be `address, phoneNo`
- when type is null or empty, return all the fields

## Solution 1

Create implementation for each type. This is the easiest way but there is too much code and it is not good.

## Solution 2

Use reflection. Can use custom fields in properties.

## Solution 3

Use `@JsonFilter("myCustomFilter")`. Can use custom fields in properties.

## Solution 4

Use properties to store a HashMap, then parse for the list and use solution #2 or #3.

This approach is the most complex but can cover a lot of cases.
