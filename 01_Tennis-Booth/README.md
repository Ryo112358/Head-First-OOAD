# Chapter 1 Notes

## Great Software in 3 easy steps:

1. Make sure your software does what the customer wants it to do.
2. Apply basic OO principles to add flexibility.
3. Strive for a maintainable, reusable design.

## Summary
- Encapsulation is breaking your application into logical parts.
- Delegation is giving another object the responsibility of handling a particular task.
- Once you've got the basic functionality of an app in place, work on refining the design so it's flexible.
- Building an application that works well but is poorly designed satisfies the customer but will leave you with pain, suffering, and lots of late nights fixing problems.

## Critical Thinking

### 1. On Comparing TennisRacket w/ TennisRacketFilterCriteria
- When I think of comparing a product with search criteria, the question I ask naturally is "Does this product fit/match the search criteria?", e.g. does the tennis racket match the filter.
  - I don't naturally ask "Does the search criteria include this racket?" as it's a more passive association.
  - Hence by adding the `tennisRacket.matches(racketFilterCriteria)` to the TennisRacket class, I meddled with encapsulation for the sake of logical cohesion (i.e. w/ the code below).
```java
if (stockRacket.matches(searchCriteria))
	matchingRackets.add(stockRacket);
```
  - No matter which class is delegated to compare the two objects, encapsulation must be broken since the objects are different. Is this a flaw in my design?