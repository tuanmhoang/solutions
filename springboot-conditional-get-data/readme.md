# Contents
- [Problem](#problem) 
- [Solution 1](#solution-1)
- [Solution 2](#solution-2)
- [Solution 3](#solution-3)
- [Solution 4](#solution-4)
- [Sample output](#sample-output)

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

Can use `Factory pattern`, when need to add/remove a part, need to add/remove the related codes.

Refer to 

```
@PostMapping("/information/v1")
```

## Solution 2

Use reflection. Can use custom fields in properties.

Refer to

```
@PostMapping("/information/v2")
```

## Solution 3

Use `@JsonFilter("myCustomFilter")`. Can use custom fields in properties.

Refer to

```
@PostMapping("/information/v3")
```

## Solution 4

Use properties to store a HashMap, then parse for the list and use solution #2 or #3.

This approach is the most complex but can cover a lot of cases.

Proposed idea. Not yet implemented.

## Sample output

### infoType is empty or null  

![image](https://user-images.githubusercontent.com/37680968/141723585-e676c61f-da20-4033-8f4f-a4c3845a3588.png)

![image](https://user-images.githubusercontent.com/37680968/141723660-f95c01ab-835b-4bd7-95da-f0df3726d232.png)

### infoType is general

![image](https://user-images.githubusercontent.com/37680968/141723789-310122c4-7931-44b0-b520-180f69d3720c.png)

### infoType is legal

![image](https://user-images.githubusercontent.com/37680968/141723840-639a1b28-1b49-43e6-80ec-d468618514a6.png)

### infoType is contact

![image](https://user-images.githubusercontent.com/37680968/141723900-cd5719d3-8d05-4337-bc1d-0a55fbf452d8.png)
