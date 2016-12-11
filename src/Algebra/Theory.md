# Theory

## Monoids

#### Definition

A Monoid M \<M;•,e> is an algebraic structure that satisfies the following rules:

1. The binary operation • on the set M is associative
2. There exists a left/right neutral element e such that e•a = a or a•e=a. If both left and right neutral element exist, then it's simply called neutral element.

## Groups

A Group G \<G;•,⁻¹,e> is an algebraic structure that satisfies the following rules:

1. The binary operation • on the set G is associative
2. There exists a left/right neutral element e such that e•a = a or a•e=a. If both left and right neutral element exist, then it's simply called neutral element.
3. For each a of G, there exists an inverse element a⁻¹ such that the following equation is satisfied: a•a⁻¹ = a⁻¹•a = e

## Rings

A Ring R \<R;+,-,0,•,1> is an algebraic structure that satisfies the following rules:

1. \<R;+,-,0> is a group
2. \<R;•,1> is a monoid
3. a•(b+c) = a•b + a•c and (a+b)•c = a•c + b•c for all a,b,c (distributivity)

## Fields

A Field F is a Ring R with an additive group and a multiplicative group (instead of a multiplicative monoid). Therefore every element (except the additive identity) is guaranteed to have a inverse element.

## Finite Fields

A finite field (also Galois field) GF is a field that contains a limited (finite) number of elements. Any finite field contains exactly qⁿ elements where q is a prime number and n is a positive integer. 
