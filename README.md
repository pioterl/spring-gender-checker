I had to create app, that can detect gender by given name.
First endpoint ("/first-variant") should return gender based on first word. For example "Jan Maria Rokita" is male, because only Jan is checked.

In second endpoint ("/second-variant") majority rule is used. For example "Jan Maria Rokita" is "inconclusive" because Jan is male, Maria is male and female name, and Rokita is unrecognized. There are also two endpoints ("/male-tokens") and ("/female-tokens") to return all available names.
